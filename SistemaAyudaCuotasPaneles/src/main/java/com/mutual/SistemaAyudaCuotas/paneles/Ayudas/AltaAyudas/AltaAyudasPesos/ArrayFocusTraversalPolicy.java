package com.mutual.SistemaAyudaCuotas.paneles.Ayudas.AltaAyudas.AltaAyudasPesos;

import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;

public class ArrayFocusTraversalPolicy extends FocusTraversalPolicy {
    private final Component[] components;

    public ArrayFocusTraversalPolicy(Component[] components) {
        if (components == null || components.length == 0) {
            throw new IllegalArgumentException("Components array cannot be null or empty.");
        }
        this.components = components;
    }

    @Override
    public Component getComponentAfter(Container container, Component component) {
        int idx = indexOf(component);
        return components[(idx + 1) % components.length];
    }

    @Override
    public Component getComponentBefore(Container container, Component component) {
        int idx = indexOf(component);
        return components[(idx - 1 + components.length) % components.length];
    }

    @Override
    public Component getDefaultComponent(Container container) {
        return components[0];
    }

    @Override
    public Component getFirstComponent(Container container) {
        return components[0];
    }

    @Override
    public Component getLastComponent(Container container) {
        return components[components.length - 1];
    }

    private int indexOf(Component component) {
        for (int i = 0; i < components.length; i++) {
            if (components[i] == component) {
                return i;
            }
        }
        return 0; // default
    }
}
