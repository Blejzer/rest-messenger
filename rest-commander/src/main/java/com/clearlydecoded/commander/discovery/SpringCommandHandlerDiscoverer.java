package com.clearlydecoded.commander.discovery;

import com.clearlydecoded.commander.Command;
import com.clearlydecoded.commander.CommandHandler;
import com.clearlydecoded.commander.CommandResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.extern.java.Log;
import org.springframework.context.ApplicationContext;

/**
 * {@link SpringCommandHandlerDiscoverer} class is a Spring Framework implementation of the
 * {@link CommandHandlerDiscoverer} interface. It uses only the Spring context to look for
 * concrete implementations of {@link CommandHandler} interface.
 */
@Log
public class SpringCommandHandlerDiscoverer implements CommandHandlerDiscoverer {

  /**
   * Spring Framework application context.
   */
  private ApplicationContext springContext;

  /**
   * Constructor.
   *
   * @param springContext Spring Framework application context.
   */
  public SpringCommandHandlerDiscoverer(ApplicationContext springContext) {
    this.springContext = springContext;
  }

  @Override
  public List<? extends CommandHandler<? extends Command<? extends CommandResponse>,
      ? extends CommandResponse>> discoverHandlers() {

    // Retrieve all spring beans that implement CommandHandler interface
    Map<String, CommandHandler> beanMap = springContext.getBeansOfType(CommandHandler.class);

    // Build a list of CommandHandlers from the bean map
    Set<String> beanNames = beanMap.keySet();
    List<CommandHandler<? extends Command<? extends CommandResponse>,
        ? extends CommandResponse>> commandHandlers;
    commandHandlers = new ArrayList<>();

    for (String beanName : beanNames) {
      CommandHandler<? extends Command<? extends CommandResponse>,
          ? extends CommandResponse> commandHandler;
      commandHandler = (CommandHandler<? extends Command<? extends CommandResponse>,
          ? extends CommandResponse>) beanMap.get(beanName);

      commandHandlers.add(commandHandler);
    }

    // Check that at least 1 command handler is discovered; log warning if not
    if (commandHandlers.size() == 0) {
      String message = "No command handlers were discovered in Spring Context.";
      message += " If you think you have CommandHandler classes implemented, check that your";
      message += " command handler classes are injected into Spring Context either by manually";
      message += " injecting them (in an @Configuration class) or by having the command handler";
      message += " classes annotated with either @Service, @Component, etc.";

      log.warning(message);
    }

    return commandHandlers;
  }
}