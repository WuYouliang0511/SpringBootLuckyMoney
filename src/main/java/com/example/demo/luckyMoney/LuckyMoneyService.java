package com.example.demo.luckyMoney;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class LuckyMoneyService {

    private LuckyMoneyRepository repository;

    @Autowired
    public LuckyMoneyService(LuckyMoneyRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void createTwoLuckyMoney() {
        LuckyMoney luckyMoney1 = new LuckyMoney();
        luckyMoney1.setProducer("邬友亮1999");
        luckyMoney1.setMoney(new BigDecimal("500"));
        repository.save(luckyMoney1);

        LuckyMoney luckyMoney2 = new LuckyMoney();
        luckyMoney2.setProducer("邬友亮2");
        luckyMoney2.setMoney(new BigDecimal("200"));
        repository.save(luckyMoney2);
    }
}
