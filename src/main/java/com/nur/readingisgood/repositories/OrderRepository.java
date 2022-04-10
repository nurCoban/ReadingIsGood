package com.nur.readingisgood.repositories;

import com.nur.readingisgood.models.MonthlyOrderStatistic;
import com.nur.readingisgood.models.Order;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
    @Query(value = "{customerId:'?0'}")
    List<Order> findByCustomerId(String customerId);

    @Aggregation(pipeline = {
            "{$match:{customerId:'?0'}}",
            "{$project: {items: { $objectToArray: '$lines' }, createdDateTime: '$createdDateTime'}}",
            "{$unwind: {path: '$items' }}",
            "{$group: {_id: {$month: '$createdDateTime'}, totalOrderCount: { $sum: 1 }, totalBookCount: { $sum: '$items.v.count' }, totalPurchasedAmount: { $sum: '$items.v.item.price' }}}",
            "{ $project: { month: { $arrayElemAt: [ ['', 'Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'], '$_id' ]}, totalOrderCount: '$totalOrderCount', totalBookCount: '$totalBookCount', totalPurchasedAmount: '$totalPurchasedAmount' } }"
    })
    List<MonthlyOrderStatistic> getMonthlyBy(String customerId);

    @Query("{'createdDateTime': {$gte: ?0, $lte:?1 }}")
    List<Order> findOrderCreatedBetween(LocalDateTime startDate, LocalDateTime endDate);
}
