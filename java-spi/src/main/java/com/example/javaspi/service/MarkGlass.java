package com.example.javaspi.service;

import com.example.javaprovider.provider.GlassProvider;

public class MarkGlass implements GlassProvider {
    @Override
    public String make() {
        return "i am a mark glass";
    }
}
