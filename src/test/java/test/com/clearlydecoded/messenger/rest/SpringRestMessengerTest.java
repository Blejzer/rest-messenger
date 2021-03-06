/*
 * Copyright 2018 Yaakov Chaikin (yaakov@ClearlyDecoded.com). Licensed under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in compliance with the License. You
 * may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0. Unless required
 * by applicable law or agreed to in writing, software distributed under the License is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See
 * the License for the specific language governing permissions and limitations under the License.
 */
package test.com.clearlydecoded.messenger.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationConfig.class)
@AutoConfigureMockMvc
public class SpringRestMessengerTest {

  @Autowired
  private MockMvc mvc;

  @Test
  public void testSendingCommand4() throws Exception {
    Message4 command4 = new Message4("Hello", new Person("Yaakov", "Chaikin"));
    Message4Response expectedResponse = new Message4Response("Echo of Hello");
    ObjectMapper mapper = new ObjectMapper();
    String command4String = mapper.writeValueAsString(command4);
    String expectedResponseString = mapper.writeValueAsString(expectedResponse);
    mvc.perform(post("/process").accept(MediaType.APPLICATION_JSON).content(command4String)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().string(expectedResponseString));
  }

  @Test
  public void testSendingCommand5Hello() throws Exception {
    Message5 command5 = new Message5("Hello");
    Message5Response expectedResponse = new Message5Response("Hi!");
    ObjectMapper mapper = new ObjectMapper();
    String command4String = mapper.writeValueAsString(command5);
    String expectedResponseString = mapper.writeValueAsString(expectedResponse);
    mvc.perform(post("/process").accept(MediaType.APPLICATION_JSON).content(command4String)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().string(expectedResponseString));
  }

  @Test
  public void testSendingCommand5Bye() throws Exception {
    Message5 command5 = new Message5("Good Bye");
    Message5Response expectedResponse = new Message5Response("Bye!");
    ObjectMapper mapper = new ObjectMapper();
    String command4String = mapper.writeValueAsString(command5);
    String expectedResponseString = mapper.writeValueAsString(expectedResponse);
    mvc.perform(post("/process").accept(MediaType.APPLICATION_JSON).content(command4String)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().string(expectedResponseString));
  }

  @Test(expected = NestedServletException.class)
  public void testSendingUnknownCommand() throws Exception {
    UnsupportedMessage unsupportedCommand = new UnsupportedMessage();
    ObjectMapper mapper = new ObjectMapper();
    String unsupportedCommandString = mapper.writeValueAsString(unsupportedCommand);
    mvc.perform(
        post("/process").accept(MediaType.APPLICATION_JSON).content(unsupportedCommandString)
            .contentType(MediaType.APPLICATION_JSON));
  }

  //  @Test
  //  public void testGetAvailableCommands() throws Exception {
  //    mvc.perform(get("/process")).andDo(print());
  ////        .contentType(MediaType.APPLICATION_JSON))
  ////        .andExpect(status().isOk())
  ////        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
  ////        .andExpect(content().string(expectedResponseString));
  //  }
}
