/*
 *  Copyright 1999-2019 Seata.io Group.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package io.seata.rm;

import io.seata.core.context.RootContext;

import java.util.concurrent.Callable;

/**
 * Template of executing business logic in a local transaction with Global lock.
 *
 * @param <T>
 * @author deyou
 * @date 2019.03.07
 */
//
public class GlobalLockTemplate<T> {

    /**
     * Execute object.
     *
     * @param business the business
     * @return the object
     * @throws Exception
     */
//
    public Object execute(Callable<T> business) throws Exception {

        Object rs = null;
        try {
            // add global lock declare  获得全局锁
            RootContext.bindGlobalLockFlag();

            // Do Your Business 执行业务方法
            rs = business.call();
        } finally {
            //clean the global lock declare 释放全局锁
            RootContext.unbindGlobalLockFlag();
        }

        return rs;
    }

}
