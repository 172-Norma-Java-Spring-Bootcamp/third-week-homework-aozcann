package com.example.thirdweekhomeworkahmetozcan.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WeatherModel {

    private Current current;
    private List<Daily> daily;
    private List<Hourly> hourly;

    @Getter
    @Setter
    public static class Current {
        private Integer dt;
        private double temp;
        private double feels_like;
        private double humidity;
        private List<Weather> weather;
    }

    @Getter
    @Setter
    public static class Hourly {
        private Integer dt;
        private double temp;
        private double feels_like;
        private double humidity;
        private List<Weather> weather;
    }

    @Getter
    @Setter
    public static class Daily {
        private Integer dt;
        private double humidity;
        private List<Weather> weather;
        private Temp temp;
        private Feels feels_like;

        @Getter
        @Setter
        public static class Temp {
            private double day;
        }

        @Getter
        @Setter
        public static class Feels {
            private double day;
        }
    }

    @Getter
    @Setter
    public static class Weather {
        private String description;
    }

}
