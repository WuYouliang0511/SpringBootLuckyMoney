package com.example.demo.luckyMoney;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

interface LuckyMoneyRepository extends JpaRepository<LuckyMoney, Integer> {

    /**
     * 获取LuckyMoney的创建者是producer，消耗者是consumer的LuckyMoney
     *
     * @param producer LuckyMoney的创建者
     * @param consumer LuckyMoney的消耗者
     * @return 创建者是producer，消耗者是consumer的LuckyMoney
     */
    @Query("select t from LuckyMoney t where t.producer = :producer and t.consumer = :consumer")
    ArrayList<LuckyMoney> getByProducerAndConsumer(@Param("producer") String producer, @Param("consumer") String consumer);

    /**
     * 获取对应id的LuckyMoney
     *
     * @param id LuckyMoney的创建者
     * @return 对应id的LuckyMoney
     */
    @Query("select t from LuckyMoney t where t.id = :id")
    ArrayList<LuckyMoney> getById(@Param("id") Integer id);


}
