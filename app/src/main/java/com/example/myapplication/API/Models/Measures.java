package com.example.myapplication.API.Models;

public class Measures {
    public Us us;
    public Metric metric;

    public Measures(Us us, Metric metric) {
        this.us = us;
        this.metric = metric;
    }

    public Us getUs() {
        return us;
    }

    public void setUs(Us us) {
        this.us = us;
    }

    public Metric getMetric() {
        return metric;
    }

    public void setMetric(Metric metric) {
        this.metric = metric;
    }
}
