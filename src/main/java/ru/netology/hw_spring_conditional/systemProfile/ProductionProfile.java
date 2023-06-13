package ru.netology.hw_spring_conditional.systemProfile;

public class ProductionProfile implements SystemProfile {
    @Override
    public String getProfile() {

        return "Current profile is production";
    }
}
