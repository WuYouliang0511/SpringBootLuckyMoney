package com.example.demo.luckyMoney;

import com.example.demo.service.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class LuckyMoneyController {

    private LuckyMoneyRepository repository;
    private LuckyMoneyService service;

    @Autowired
    public LuckyMoneyController(LuckyMoneyRepository repository, LuckyMoneyService service) {
        this.repository = repository;
        this.service = service;
    }

    /**
     * Get all lucky money
     *
     * @return all lucky money
     */
    @GetMapping("luckyMoneys")
    public List<LuckyMoney> getAll() {
        WebSocketServer.sendMessage("13213213213213213");
        return repository.findAll();
    }

    /**
     * Get a lucky money by id
     * PathVariable：斜杠后直接带参数
     *
     * @param id LuckyMoney的id
     * @return 对应id的LuckyMoney
     */
    @GetMapping("luckyMoney/{id}")
    public LuckyMoney getById(@PathVariable("id") Integer id) {
        return repository.findById(id).orElse(null);
    }

    /**
     * Get a lucky money by producer and consumer
     * PathVariable：“/”后直接带参数,参数之间用“/”隔开
     *
     * @param producer LuckyMoney的producer
     * @param consumer LuckyMoney的consumer
     * @return 对应producer和consumer的LuckyMoney
     */
    @GetMapping("luckyMoney/{producer}/{consumer}")
    public ArrayList<LuckyMoney> getByProducerAndConsumer(
            @PathVariable("producer") String producer,
            @PathVariable("consumer") String consumer) {
        return repository.getByProducerAndConsumer(producer, consumer);
    }

    /**
     * Get a lucky money by id
     * RequestParam:“？参数名=参数值”的形式访问
     *
     * @param id LuckyMoney的id
     * @return 对应id的LuckyMoney
     */
    @GetMapping("luckyMoney.id")
    public LuckyMoney getById2(@RequestParam("id") Integer id) {
        return repository.findById(id).orElse(null);
    }

    /**
     * Get a lucky money by id or producer and consumer
     * RequestParam:“？参数名=参数值”的形式访问  参数之间用“&”隔开
     *
     * @param producer LuckyMoney的producer
     * @param consumer LuckyMoney的consumer
     * @return 对应producer和consumer的LuckyMoney
     */
    @GetMapping("luckyMoney.pc")
    public ArrayList<LuckyMoney> getByProducerAndConsumer2(
            @RequestParam(value = "producer", required = true) String producer,
            @RequestParam(value = "consumer", defaultValue = "www", required = false) String consumer) {
        return repository.getByProducerAndConsumer(producer, consumer);
    }

    /**
     * Create a lucky money,producer and money are necessary
     *
     * @param producer producer of luckyMoney
     * @param money    Amount of luckyMoney
     * @return luckyMoney just been created
     */
    @PostMapping("luckyMoney/{producer}/{money}")
    public LuckyMoney create1(@PathVariable("producer") String producer,
                              @PathVariable("money") BigDecimal money) {
        LuckyMoney luckyMoney = new LuckyMoney();
        luckyMoney.setProducer(producer);
        luckyMoney.setMoney(money);
        return repository.save(luckyMoney);
    }

    /**
     * Create a lucky money,producer and money are necessary
     *
     * @param producer producer of luckyMoney
     * @param money    Amount of luckyMoney
     * @return luckyMoney just been created
     */
    @PostMapping("luckyMoney")
    public LuckyMoney create(@RequestParam("producer") String producer,
                             @RequestParam("money") BigDecimal money) {
        LuckyMoney luckyMoney = new LuckyMoney();
        luckyMoney.setProducer(producer);
        luckyMoney.setMoney(money);
        return repository.save(luckyMoney);
    }

    /**
     * Create a lucky money
     * RequestBody在postman中的数据为  body   raw
     *
     * @param luckyMoney producer of luckyMoney
     * @return luckyMoney just been created
     */
    @PostMapping("luckyMoney/create")
    public LuckyMoney create8(@RequestBody LuckyMoney luckyMoney) {
        //再次对luckyMoney进行依次列判断
        //决定是否save
        return repository.save(luckyMoney);
    }

    @PostMapping("luckyMoney/create1")
    public LuckyMoney create81(@RequestBody String producer) {
        LuckyMoney luckyMoney = new LuckyMoney();
        luckyMoney.setProducer(producer);
        return repository.save(luckyMoney);
    }


    /**
     * update a lucky money
     *
     * @param id
     * @param consumer
     * @return
     */
    @PutMapping("luckyMoney/{id}")
    public LuckyMoney update(@PathVariable("id") Integer id,
                             @RequestParam("consumer") String consumer) {
        Optional<LuckyMoney> optional = repository.findById(id);
        if (optional.isPresent()) {
            LuckyMoney luckyMoney = optional.get();
            luckyMoney.setConsumer(consumer);
            return repository.save(luckyMoney);
        }
        return null;
    }


    @PostMapping("luckyMoney/two")
    public void createTwoLuckyMoney() {
        service.createTwoLuckyMoney();
    }


    @PostMapping(value = "postString")
    public String create(@RequestBody String lu) {
        System.out.println(lu);
        return "服务端接收到String";
    }

    @PostMapping(value = "postFile")
    public String create(@RequestBody File file) throws IOException {

//        FileOutputStream stream = new FileOutputStream(file);
//        OutputStreamWriter writer = new OutputStreamWriter(stream);
//        BufferedWriter bufferedWriter = new BufferedWriter(writer);
//        bufferedWriter.flush();
//        bufferedWriter.close();
        return "服务端接收到File";
    }

    @PostMapping("/uploadFile")
    public boolean uploadFile(@RequestParam("file") MultipartFile multipartFile) throws IOException {

        File toFile = null;
        if (multipartFile.equals("") || multipartFile.getSize() <= 0) {
            return false;
        } else {
            InputStream ins = null;
            ins = multipartFile.getInputStream();
            String path = "/usr/wyl/resource";
            toFile = new File(path, "123.txt");
            boolean a = inputStreamToFile(ins, toFile);
            ins.close();
            return a;
        }
    }

    //获取流文件
    private static boolean inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

}
