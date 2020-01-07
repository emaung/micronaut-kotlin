/*
 * Copyright 2017-2019 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.micronaut.context.env.hocon

import io.micronaut.context.env.DefaultEnvironment
import org.junit.jupiter.api.Test

class HoconPropertySourceLoaderTest {

    @Test
    fun testPropertySourceLoader() {
        val env = DefaultEnvironment()
        env.start()

        val value = env.getProperty("micronaut.server.port", Integer::class.java)
        assert(
                value.get().toInt() == 8081
        )
    }

    @Test
    fun testPropertySourceLoaderOrder() {
        System.setProperty("test-property", "good value")

        val env = DefaultEnvironment()
        env.start()

        val value = env.getProperty("test-property", String::class.java)
        assert(
                value.get() == "good value"
        )

        System.clearProperty("test-property")
    }
  
    fun testPropertySourceLoaderEnvironmentVariable() {
        val env = DefaultEnvironment()
        env.start()

        val value = env.getProperty("custom.user", String::class.java)
        assert(
                value.get() == System.getProperty("user.name")
        )
    }

}