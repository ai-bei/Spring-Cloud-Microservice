package com.iotknowyou.SpringRestTemplate.controller;

import com.iotknowyou.SpringRestTemplate.entity.HouseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HouseClientController {

    @Autowired
    private RestTemplate restTemplate;

    /*

    对于GET 请求

      获取结果可以通过 RestTemplate 的 getForObject 方法来实现
            参数说明：
                uri : 有两种方式，一种是 字符串， 一种是 URI形式
                responseType ：返回值的类型
                uriVariables : PathVariable参数，有两种形式，一种是 可变参数，另一种是 Map形式

        public <T> T getForObject(String url, Class<T> responseType, Object... uriVariables)

        public <T> T getForObject(String url, Class<T> responseType, Map<String, ?> uriVariables)

        public <T> T getForObject(URI url, Class<T> responseType)



        除了使用 getForObject 来调用调用接口获取参数，我们还可以使用，getForObject 来获取数据
            使用这种方式，可以获取返回的状态码，请求头等信息 ，通过getBody 获取响应的内容。

        public <T> ResponseEntity<T> getForEntity(String url, Class<T> responseType, Object... uriVariables)

        public <T> ResponseEntity<T> getForEntity(String url, Class<T> responseType, Map<String, ?> uriVariables)

        public <T> ResponseEntity<T> getForEntity(URI url, Class<T> responseType)


     对于 Pose请求：
        可以通过 postForObject 来获取数据 ，postForObject 有下面三个重载的方法。

                uri : 有两种方式，一种是 字符串， 一种是 URI形式
                responseType ：返回值的类型
                uriVariables : PathVariable参数，有两种形式，一种是 可变参数，另一种是 Map形式
                request ： 参数
        public <T> T postForObject(String url, @Nullable Object request, Class<T> responseType, Object... uriVariables)

        public <T> T postForObject(String url, @Nullable Object request, Class<T> responseType, Map<String, ?> uriVariables)

         public <T> T postForObject(URI url, @Nullable Object request, Class<T> responseType)

     同样可以使用：postForEntity
        public <T> ResponseEntity<T> postForEntity(String url, @Nullable Object request, Class<T> responseType, Object... uriVariables)

        public <T> ResponseEntity<T> postForEntity(String url, @Nullable Object request, Class<T> responseType, Map<String, ?> uriVariables)

        public <T> ResponseEntity<T> postForEntity(URI url, @Nullable Object request, Class<T> responseType)

        除去 get 和 post 方法， 还提供了 put 和 delete 方法，以及提供了 exchange 可以4中请求方式。
    */



    @GetMapping("/call/data")
    public HouseInfo getData(@RequestParam("name") String name) {
        return restTemplate.getForObject(
                "http://localhost:8081/house/data?name="+name, HouseInfo.class);
    }

    @GetMapping("/call/data/{name}")
    public String getData2(@PathVariable("name") String name) {
        return restTemplate.getForObject(
                "http://localhost:8081/house/data/{name}", String.class, name);
    }

    @GetMapping("/call/dataEntity")
    public HouseInfo getDataEntity(@RequestParam("name") String name) {
        ResponseEntity<HouseInfo> responseEntity = restTemplate.getForEntity(
                "http://localhost:8081/house/data?name="+name, HouseInfo.class);
        if(responseEntity.getStatusCodeValue() == 200) {
            return responseEntity.getBody();
        }
        return null;
    }

    @GetMapping("/call/save")
    public Long add() {
        HouseInfo houseInfo = new HouseInfo();
        houseInfo.setCity("上海");
        houseInfo.setRegion("虹口");
        houseInfo.setName("XXX");
        Long id = restTemplate.postForObject(
                "http://localhost:8081/house/save", houseInfo, Long.class);
        return id;
    }


}
