package dev.dan.mcutils.item.handlers;

import dev.dan.mcutils.exceptions.TypeNotAcceptedException;
import dev.dan.mcutils.pluginmanager.ManagedPlugin;
import lombok.Getter;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;

import java.lang.reflect.ParameterizedType;

public abstract class ItemHandler<E> {

    @Getter
    private ManagedPlugin plugin;

    @Getter
    private EventPriority priority;

    public ItemHandler(ManagedPlugin plugin, EventPriority priority) {
        this.plugin = plugin;
        this.priority = priority;
        validateEventAssignable();
    }

    private void validateEventAssignable() {
        ParameterizedType genericSuperclassType =
                (ParameterizedType) getClass().getGenericSuperclass();
        Class<?> eventType = (Class<?>) genericSuperclassType.getActualTypeArguments()[0];

        if (!Event.class.isAssignableFrom(eventType)) {
            try {
                throw new TypeNotAcceptedException(Event.class, eventType);
            } catch (TypeNotAcceptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public abstract void onEvent(E event);

    public Class<? extends Event> getEventClass(){
        ParameterizedType p = (ParameterizedType) getClass().getGenericSuperclass();
        return (Class<? extends Event>) p.getActualTypeArguments()[0];
    }
}
