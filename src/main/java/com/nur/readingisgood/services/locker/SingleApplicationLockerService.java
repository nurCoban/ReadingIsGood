package com.nur.readingisgood.services.locker;


import com.google.common.util.concurrent.Striped;
import org.springframework.stereotype.Service;

import java.util.concurrent.locks.Lock;

@Service
class SingleApplicationLockerService implements ILockerService {
    private final Striped<Lock> locks;

    public SingleApplicationLockerService() {
        locks = Striped.lock(10);
    }

    @Override
    public Lock createLock(String key) {
        Lock result = locks.get(key);
        return result;
    }
}
