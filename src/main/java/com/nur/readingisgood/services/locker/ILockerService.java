package com.nur.readingisgood.services.locker;

import java.util.concurrent.locks.Lock;

public interface ILockerService {
    Lock createLock(String key);
}
