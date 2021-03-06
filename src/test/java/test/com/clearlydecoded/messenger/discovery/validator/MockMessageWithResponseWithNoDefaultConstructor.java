package test.com.clearlydecoded.messenger.discovery.validator;

import com.clearlydecoded.messenger.Message;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MockMessageWithResponseWithNoDefaultConstructor implements
    Message<MockResponseWithNoDefaultConstructor> {

  public static final String TYPE = "MockMessageWithResponseNoDefaultConstructor";

  private final String type = TYPE;

  @Override
  public String getType() {
    return type;
  }
}
