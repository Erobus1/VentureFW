package me.erobus.bot.commands;

import me.erobus.bot.commands.util.Permission;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface Command {
    String name();
    String description();
    String usage() default "";
    String category() default "general";
    String[] aliases() default {};
    Permission permission() default Permission.NONE;
}
