package com.example.apitest;

public class Recipe {
//            "DET_URL":"http://www.okdab.com/consumer/recipe/recipeView.do?recipeSn=1",
//            "IRDNT_CODE":"곡류",
//            "IMG_URL":"http://file.okdab.com/UserFiles/searching/recipe/000200.jpg",
//            "NATION_CODE":"3020001",
//            "SUMRY":"육수로 지은 밥에 야채를 듬뿍 넣은 영양만점 나물비빔밥!",
//            "CALORIE":"580Kcal",
//            "TY_CODE":"3010001",
//            "RECIPE_NM_KO":"나물비빔밥",
//            "RN":1,
//            "QNT":"4인분",
//            "PC_NM":"5,000원",
//            "TY_NM":"밥",
//            "LEVEL_NM":"보통",
//            "RECIPE_ID":1,
//            "NATION_NM":"한식",
//            "COOKING_TIME":"60분"
private String detUrl;
    private String ingredient;
    private String imageUrl;
    private String nationCode;
    private String summary;
    private String calorie;
    private String typeCode;
    private String name;
    private String recipeNum;
    private String Quantity;
    private String price;
    private String typeName;
    private String level;
    private String nationName;
    private String cookingTime;

    public String getDetUrl() {
        return detUrl;
    }

    public void setDetUrl(String detUrl) {
        this.detUrl = detUrl;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getNationCode() {
        return nationCode;
    }

    public void setNationCode(String nationCode) {
        this.nationCode = nationCode;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCalorie() {
        return calorie;
    }

    public void setCalorie(String calorie) {
        this.calorie = calorie;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRecipeNum() {
        return recipeNum;
    }

    public void setRecipeNum(String recipeNum) {
        this.recipeNum = recipeNum;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getNationName() {
        return nationName;
    }

    public void setNationName(String nationName) {
        this.nationName = nationName;
    }

    public String getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(String cookingTime) {
        this.cookingTime = cookingTime;
    }
}
