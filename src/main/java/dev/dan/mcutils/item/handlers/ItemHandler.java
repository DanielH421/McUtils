package dev.dan.mcutils.item.handlers;

import dev.dan.mcutils.McUtils;
import dev.dan.mcutils.exceptions.TypeNotAcceptedException;
import dev.dan.mcutils.pluginmanager.ManagedPlugin;
import lombok.Getter;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.ParameterizedType;

@Getter
public abstract class ItemHandler<E> {

    private final ManagedPlugin plugin;

    private final EventPriority priority;

    public ItemHandler(@NotNull ManagedPlugin plugin, @NotNull EventPriority priority) {
        this.plugin = plugin;
        this.priority = priority;
        try{
            validateEventAssignable();
        } catch (TypeNotAcceptedException e){
            McUtils.getInstance().getCustomLogger().printStackTrace(e);
        }

    }


    private void validateEventAssignable() throws TypeNotAcceptedException {
        ParameterizedType genericSuperclassType =
                (ParameterizedType) getClass().getGenericSuperclass();
        Class<?> eventType = (Class<?>) genericSuperclassType.getActualTypeArguments()[0];

        if (!Event.class.isAssignableFrom(eventType)) {
            throw new TypeNotAcceptedException(Event.class, eventType);
        }
    }


    public abstract void onEvent(E event);


    public Class<? extends Event> getEventClass(){
        ParameterizedType p = (ParameterizedType) getClass().getGenericSuperclass();
        return (Class<? extends Event>) p.getActualTypeArguments()[0];
    }
}
