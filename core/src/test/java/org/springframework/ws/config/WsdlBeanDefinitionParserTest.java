/*
 * Copyright 2005-2010 the original author or authors.
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

package org.springframework.ws.config;

import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.ws.wsdl.wsdl11.SimpleWsdl11Definition;
import org.springframework.xml.xsd.commons.CommonsXsdSchemaCollection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Arjen Poutsma
 */
public class WsdlBeanDefinitionParserTest {

    private ApplicationContext applicationContext;

    @Before
    public void setUp() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("wsdlBeanDefinitionParserTest.xml", getClass());
    }

    @Test
    public void staticWsdl() throws Exception {
        Map<String, SimpleWsdl11Definition> result = applicationContext.getBeansOfType(SimpleWsdl11Definition.class);
        Assert.assertFalse("no WSDL definitions found", result.isEmpty());
        String beanName = result.keySet().iterator().next();
        Assert.assertEquals("invalid bean name", "simple", beanName);
    }

    @Test
    public void dynamicWsdl() throws Exception {
        Map<String, ?> result = applicationContext.getBeansOfType(DefaultWsdl11Definition.class);
        Assert.assertFalse("no WSDL definitions found", result.isEmpty());

        result = applicationContext.getBeansOfType(CommonsXsdSchemaCollection.class);
        Assert.assertFalse("no XSD definitions found", result.isEmpty());
    }
}
