package com.example.myapplication.API.Models;

import java.util.ArrayList;

public class RecipeDetailsResponse {
    public int id;
    public String title;
    public String image;
    public String imageType;
    public int servings;
    public int readyInMinutes;
    public String license;
    public String sourceName;
    public String sourceUrl;
    public String spoonacularSourceUrl;
    public int aggregateLikes;
    public double healthScore;
    public double spoonacularScore;
    public double pricePerServing;
    public ArrayList<Object> analyzedInstructions;
    public boolean cheap;
    public String creditsText;
    public ArrayList<Object> cuisines;
    public boolean dairyFree;
    public ArrayList<Object> diets;
    public String gaps;
    public boolean glutenFree;
    public String instructions;
    public boolean ketogenic;
    public boolean lowFodmap;
    public ArrayList<Object> occasions;
    public boolean sustainable;
    public boolean vegan;
    public boolean vegetarian;
    public boolean veryHealthy;
    public boolean veryPopular;
    public boolean whole30;
    public int weightWatcherSmartPoints;
    public ArrayList<String> dishTypes;
    public ArrayList<ExtendedIngredient> extendedIngredients;
    public String summary;
    public WinePairing winePairing;

    public RecipeDetailsResponse(int id, String title, String image, String imageType, int servings, int readyInMinutes, String license, String sourceName, String sourceUrl, String spoonacularSourceUrl, int aggregateLikes, double healthScore, double spoonacularScore, double pricePerServing, ArrayList<Object> analyzedInstructions, boolean cheap, String creditsText, ArrayList<Object> cuisines, boolean dairyFree, ArrayList<Object> diets, String gaps, boolean glutenFree, String instructions, boolean ketogenic, boolean lowFodmap, ArrayList<Object> occasions, boolean sustainable, boolean vegan, boolean vegetarian, boolean veryHealthy, boolean veryPopular, boolean whole30, int weightWatcherSmartPoints, ArrayList<String> dishTypes, ArrayList<ExtendedIngredient> extendedIngredients, String summary, WinePairing winePairing) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.imageType = imageType;
        this.servings = servings;
        this.readyInMinutes = readyInMinutes;
        this.license = license;
        this.sourceName = sourceName;
        this.sourceUrl = sourceUrl;
        this.spoonacularSourceUrl = spoonacularSourceUrl;
        this.aggregateLikes = aggregateLikes;
        this.healthScore = healthScore;
        this.spoonacularScore = spoonacularScore;
        this.pricePerServing = pricePerServing;
        this.analyzedInstructions = analyzedInstructions;
        this.cheap = cheap;
        this.creditsText = creditsText;
        this.cuisines = cuisines;
        this.dairyFree = dairyFree;
        this.diets = diets;
        this.gaps = gaps;
        this.glutenFree = glutenFree;
        this.instructions = instructions;
        this.ketogenic = ketogenic;
        this.lowFodmap = lowFodmap;
        this.occasions = occasions;
        this.sustainable = sustainable;
        this.vegan = vegan;
        this.vegetarian = vegetarian;
        this.veryHealthy = veryHealthy;
        this.veryPopular = veryPopular;
        this.whole30 = whole30;
        this.weightWatcherSmartPoints = weightWatcherSmartPoints;
        this.dishTypes = dishTypes;
        this.extendedIngredients = extendedIngredients;
        this.summary = summary;
        this.winePairing = winePairing;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public int getReadyInMinutes() {
        return readyInMinutes;
    }

    public void setReadyInMinutes(int readyInMinutes) {
        this.readyInMinutes = readyInMinutes;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getSpoonacularSourceUrl() {
        return spoonacularSourceUrl;
    }

    public void setSpoonacularSourceUrl(String spoonacularSourceUrl) {
        this.spoonacularSourceUrl = spoonacularSourceUrl;
    }

    public int getAggregateLikes() {
        return aggregateLikes;
    }

    public void setAggregateLikes(int aggregateLikes) {
        this.aggregateLikes = aggregateLikes;
    }

    public double getHealthScore() {
        return healthScore;
    }

    public void setHealthScore(double healthScore) {
        this.healthScore = healthScore;
    }

    public double getSpoonacularScore() {
        return spoonacularScore;
    }

    public void setSpoonacularScore(double spoonacularScore) {
        this.spoonacularScore = spoonacularScore;
    }

    public double getPricePerServing() {
        return pricePerServing;
    }

    public void setPricePerServing(double pricePerServing) {
        this.pricePerServing = pricePerServing;
    }

    public ArrayList<Object> getAnalyzedInstructions() {
        return analyzedInstructions;
    }

    public void setAnalyzedInstructions(ArrayList<Object> analyzedInstructions) {
        this.analyzedInstructions = analyzedInstructions;
    }

    public boolean isCheap() {
        return cheap;
    }

    public void setCheap(boolean cheap) {
        this.cheap = cheap;
    }

    public String getCreditsText() {
        return creditsText;
    }

    public void setCreditsText(String creditsText) {
        this.creditsText = creditsText;
    }

    public ArrayList<Object> getCuisines() {
        return cuisines;
    }

    public void setCuisines(ArrayList<Object> cuisines) {
        this.cuisines = cuisines;
    }

    public boolean isDairyFree() {
        return dairyFree;
    }

    public void setDairyFree(boolean dairyFree) {
        this.dairyFree = dairyFree;
    }

    public ArrayList<Object> getDiets() {
        return diets;
    }

    public void setDiets(ArrayList<Object> diets) {
        this.diets = diets;
    }

    public String getGaps() {
        return gaps;
    }

    public void setGaps(String gaps) {
        this.gaps = gaps;
    }

    public boolean isGlutenFree() {
        return glutenFree;
    }

    public void setGlutenFree(boolean glutenFree) {
        this.glutenFree = glutenFree;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public boolean isKetogenic() {
        return ketogenic;
    }

    public void setKetogenic(boolean ketogenic) {
        this.ketogenic = ketogenic;
    }

    public boolean isLowFodmap() {
        return lowFodmap;
    }

    public void setLowFodmap(boolean lowFodmap) {
        this.lowFodmap = lowFodmap;
    }

    public ArrayList<Object> getOccasions() {
        return occasions;
    }

    public void setOccasions(ArrayList<Object> occasions) {
        this.occasions = occasions;
    }

    public boolean isSustainable() {
        return sustainable;
    }

    public void setSustainable(boolean sustainable) {
        this.sustainable = sustainable;
    }

    public boolean isVegan() {
        return vegan;
    }

    public void setVegan(boolean vegan) {
        this.vegan = vegan;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public boolean isVeryHealthy() {
        return veryHealthy;
    }

    public void setVeryHealthy(boolean veryHealthy) {
        this.veryHealthy = veryHealthy;
    }

    public boolean isVeryPopular() {
        return veryPopular;
    }

    public void setVeryPopular(boolean veryPopular) {
        this.veryPopular = veryPopular;
    }

    public boolean isWhole30() {
        return whole30;
    }

    public void setWhole30(boolean whole30) {
        this.whole30 = whole30;
    }

    public int getWeightWatcherSmartPoints() {
        return weightWatcherSmartPoints;
    }

    public void setWeightWatcherSmartPoints(int weightWatcherSmartPoints) {
        this.weightWatcherSmartPoints = weightWatcherSmartPoints;
    }

    public ArrayList<String> getDishTypes() {
        return dishTypes;
    }

    public void setDishTypes(ArrayList<String> dishTypes) {
        this.dishTypes = dishTypes;
    }

    public ArrayList<ExtendedIngredient> getExtendedIngredients() {
        return extendedIngredients;
    }

    public void setExtendedIngredients(ArrayList<ExtendedIngredient> extendedIngredients) {
        this.extendedIngredients = extendedIngredients;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public WinePairing getWinePairing() {
        return winePairing;
    }

    public void setWinePairing(WinePairing winePairing) {
        this.winePairing = winePairing;
    }
}
