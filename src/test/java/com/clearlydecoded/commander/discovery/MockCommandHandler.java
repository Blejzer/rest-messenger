package com.clearlydecoded.commander.discovery;

import com.clearlydecoded.commander.CommandHandler;
import com.clearlydecoded.commander.CommandHandlerException;

public class MockCommandHandler implements CommandHandler<MockCommand, MockCommandResponse> {

  @Override
  public MockCommandResponse execute(MockCommand command) throws CommandHandlerException {
    return null;
  }

  @Override
  public String getCompatibleCommandType() {
    return MockCommand.TYPE;
  }

  @Override
  public Class<MockCommand> getCompatibleCommandClassType() {
    return MockCommand.class;
  }
}
