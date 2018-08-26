package com.springboot.client.service;

import com.springboot.client.model.CreateUserRequest;
import com.springboot.client.model.User;
import com.springboot.client.model.UserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class UserClientService {
    Logger logger = LoggerFactory.getLogger(UserClientService.class);

    @Value("${rest.base}")
    private String REST_API_BASE_URL;

    private String getListUrl(int page, int size){
        String url = String.format(REST_API_BASE_URL + "?page=%d&size=%d", page, size);
        return url;
    }

    private String getUserUrl(String id){
        return String.format(REST_API_BASE_URL + "/%s", id);
    }

    //*///To consume User REST API Service
    public List<User> list(int page, int size) {
        String listUrl = getListUrl(page, size);
        User[] users = new RestTemplate().getForObject(listUrl, User[].class);
        return Arrays.asList(users);
    }

    public User get(String idString) {
        String getUrl = getUserUrl(idString);
        User user = new RestTemplate().getForObject(getUrl, User.class);
        return user;
    }

    private <T> HttpEntity<T> asEntity(T instance){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<T> entity = new HttpEntity<T>(instance, httpHeaders);
        return entity;
    }

    public User create(User userToCreate){
        if(userToCreate == null)
            return null;

        try {
            String createUrl = getUserUrl("");
            HttpEntity<User> entity = asEntity(userToCreate);
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.put(createUrl, entity);
            return userToCreate;
        }catch (Exception ex){
            return null;
        }
    }

    public User update(String idString, User userRequest) {
        String updateUrl = getUserUrl(idString);
        HttpEntity<User> entity = asEntity(userRequest);

        RestTemplate restTemplate = new RestTemplate();
        User updatedUser = restTemplate.postForObject(updateUrl, entity, User.class);
        return updatedUser;
    }

    public User delete(String idString) {
        User target = get(idString);
        if(target == null)
            return null;
        String deleteUrl = getUserUrl(idString);
        new RestTemplate().delete(deleteUrl);
        return target;
    }

    /*/ Setup as Dummy
    private static String getUsersJson() {
        return "[\n" +
                "  {\n" +
                "    \"email\": \"fgs.wd@zheev.com\",\n" +
                "    \"name\": \"fgs wd\",\n" +
                "    \"id\": \"1\",\n" +
                "    \"userName\": \"fgs\",\n" +
                "    \"password\": \"********\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"email\": \"rz.co@gvtsc.com\",\n" +
                "    \"name\": \"rz co\",\n" +
                "    \"id\": \"2\",\n" +
                "    \"userName\": \"rz\",\n" +
                "    \"password\": \"********\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"email\": \"hua.loz@zcqyd.com\",\n" +
                "    \"name\": \"hua loz\",\n" +
                "    \"id\": \"3\",\n" +
                "    \"userName\": \"hua\",\n" +
                "    \"password\": \"********\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"email\": \"yeip.yk@asach.com\",\n" +
                "    \"name\": \"yeip yk\",\n" +
                "    \"id\": \"4\",\n" +
                "    \"userName\": \"yeip\",\n" +
                "    \"password\": \"********\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"email\": \"durt.vunc@kmewx.com\",\n" +
                "    \"name\": \"durt vunc\",\n" +
                "    \"id\": \"5\",\n" +
                "    \"userName\": \"durt\",\n" +
                "    \"password\": \"********\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"email\": \"clwz.xuvo@wjvvi.com\",\n" +
                "    \"name\": \"clwz xuvo\",\n" +
                "    \"id\": \"6\",\n" +
                "    \"userName\": \"clwz\",\n" +
                "    \"password\": \"********\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"email\": \"ckxv.cpoh@bqibk.com\",\n" +
                "    \"name\": \"ckxv cpoh\",\n" +
                "    \"id\": \"7\",\n" +
                "    \"userName\": \"ckxv\",\n" +
                "    \"password\": \"********\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"email\": \"wlfq.smhm@vagrc.com\",\n" +
                "    \"name\": \"wlfq smhm\",\n" +
                "    \"id\": \"8\",\n" +
                "    \"userName\": \"wlfq\",\n" +
                "    \"password\": \"********\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"email\": \"he.sg@ycwvj.com\",\n" +
                "    \"name\": \"he sg\",\n" +
                "    \"id\": \"9\",\n" +
                "    \"userName\": \"he\",\n" +
                "    \"password\": \"********\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"email\": \"xzron.lbv@zddcw.com\",\n" +
                "    \"name\": \"xzron lbv\",\n" +
                "    \"id\": \"10\",\n" +
                "    \"userName\": \"xzron\",\n" +
                "    \"password\": \"********\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"email\": \"hahmtv.enmj@jihmz.com\",\n" +
                "    \"name\": \"hahmtv enmj\",\n" +
                "    \"id\": \"11\",\n" +
                "    \"userName\": \"hahmtv\",\n" +
                "    \"password\": \"********\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"email\": \"eyr.pir@malyb.com\",\n" +
                "    \"name\": \"eyr pir\",\n" +
                "    \"id\": \"12\",\n" +
                "    \"userName\": \"eyr\",\n" +
                "    \"password\": \"********\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"email\": \"hhcdht.lcjx@wgotp.com\",\n" +
                "    \"name\": \"hhcdht lcjx\",\n" +
                "    \"id\": \"13\",\n" +
                "    \"userName\": \"hhcdht\",\n" +
                "    \"password\": \"********\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"email\": \"rpbcj.pri@jmvcx.com\",\n" +
                "    \"name\": \"rpbcj pri\",\n" +
                "    \"id\": \"14\",\n" +
                "    \"userName\": \"rpbcj\",\n" +
                "    \"password\": \"********\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"email\": \"yqyq.tre@xvptp.com\",\n" +
                "    \"name\": \"yqyq tre\",\n" +
                "    \"id\": \"15\",\n" +
                "    \"userName\": \"yqyq\",\n" +
                "    \"password\": \"********\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"email\": \"xa.guf@euzhm.com\",\n" +
                "    \"name\": \"xa guf\",\n" +
                "    \"id\": \"16\",\n" +
                "    \"userName\": \"xa\",\n" +
                "    \"password\": \"********\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"email\": \"qmerdg.jjns@cwzhr.com\",\n" +
                "    \"name\": \"qmerdg jjns\",\n" +
                "    \"id\": \"17\",\n" +
                "    \"userName\": \"qmerdg\",\n" +
                "    \"password\": \"********\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"email\": \"qplgwb.ugmn@uhsba.com\",\n" +
                "    \"name\": \"qplgwb ugmn\",\n" +
                "    \"id\": \"18\",\n" +
                "    \"userName\": \"qplgwb\",\n" +
                "    \"password\": \"********\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"email\": \"sl.wpii@gfcde.com\",\n" +
                "    \"name\": \"sl wpii\",\n" +
                "    \"id\": \"19\",\n" +
                "    \"userName\": \"sl\",\n" +
                "    \"password\": \"********\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"email\": \"pgzdg.zdqn@xesnz.com\",\n" +
                "    \"name\": \"pgzdg zdqn\",\n" +
                "    \"id\": \"20\",\n" +
                "    \"userName\": \"pgzdg\",\n" +
                "    \"password\": \"********\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"email\": \"dux.ssan@kcjzx.com\",\n" +
                "    \"name\": \"dux ssan\",\n" +
                "    \"id\": \"21\",\n" +
                "    \"userName\": \"dux\",\n" +
                "    \"password\": \"********\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"email\": \"lu.xzg@rjxcj.com\",\n" +
                "    \"name\": \"lu xzg\",\n" +
                "    \"id\": \"22\",\n" +
                "    \"userName\": \"lu\",\n" +
                "    \"password\": \"********\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"email\": \"atit.dzpk@ldvrc.com\",\n" +
                "    \"name\": \"atit dzpk\",\n" +
                "    \"id\": \"23\",\n" +
                "    \"userName\": \"atit\",\n" +
                "    \"password\": \"********\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"email\": \"rhim.deoc@bmgnn.com\",\n" +
                "    \"name\": \"rhim deoc\",\n" +
                "    \"id\": \"24\",\n" +
                "    \"userName\": \"rhim\",\n" +
                "    \"password\": \"********\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"email\": \"oop.tb@qpegi.com\",\n" +
                "    \"name\": \"oop tb\",\n" +
                "    \"id\": \"25\",\n" +
                "    \"userName\": \"oop\",\n" +
                "    \"password\": \"********\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"email\": \"wrr.nqrv@cbcye.com\",\n" +
                "    \"name\": \"wrr nqrv\",\n" +
                "    \"id\": \"26\",\n" +
                "    \"userName\": \"wrr\",\n" +
                "    \"password\": \"********\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"email\": \"cnbu.bo@tmfvx.com\",\n" +
                "    \"name\": \"cnbu bo\",\n" +
                "    \"id\": \"27\",\n" +
                "    \"userName\": \"cnbu\",\n" +
                "    \"password\": \"********\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"email\": \"xys.tzd@slrgb.com\",\n" +
                "    \"name\": \"xys tzd\",\n" +
                "    \"id\": \"28\",\n" +
                "    \"userName\": \"xys\",\n" +
                "    \"password\": \"********\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"email\": \"uhjb.wg@htupy.com\",\n" +
                "    \"name\": \"uhjb wg\",\n" +
                "    \"id\": \"29\",\n" +
                "    \"userName\": \"uhjb\",\n" +
                "    \"password\": \"********\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"email\": \"umacd.wypl@zzqmn.com\",\n" +
                "    \"name\": \"umacd wypl\",\n" +
                "    \"id\": \"30\",\n" +
                "    \"userName\": \"umacd\",\n" +
                "    \"password\": \"********\"\n" +
                "  }\n" +
                "]\n";
    }

    private static List<User> userRepository = new ArrayList<>();

    public UserClientService(){
        if(!userRepository.isEmpty())
            return;

        ObjectMapper mapper = new ObjectMapper();
        try {
            userRepository = mapper.readValue(getUsersJson(), List.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<User> list(int page, int size) {
        if(page < 0 || size < 0)
            return null;

        List<User> selected = userRepository.stream()
                .skip(page * size)
                .limit(size)
                .collect(Collectors.toList());
        return selected;
    }

    public User get(String idString)
            throws IllegalArgumentException, NumberFormatException {
        User user = userRepository.stream()
                .filter(u -> u.getId().equals(idString))
                .findFirst().orElse(null);
        return user;
    }

    public User create(User userRequest){
        if(userRequest != null){
            userRepository.add(userRequest);
        }
        return userRequest;
    }

    public User update(String idString, User userRequest)
            throws IllegalArgumentException, NumberFormatException {
        User target = get(idString);
        if(target == null)
            throw new IllegalArgumentException("unfound: " + idString);
        target.setEmail(userRequest.getEmail());
        target.setName(userRequest.getName());
        target.setUserName(userRequest.getUserName());
        return target;
    }

    public User delete(String idString)
            throws IllegalArgumentException, NumberFormatException{
        User target = get(idString);
        if(target == null)
            throw new IllegalArgumentException("unfound: " + idString);
        userRepository.remove(target);
        return target;
    }
    /*///
}
