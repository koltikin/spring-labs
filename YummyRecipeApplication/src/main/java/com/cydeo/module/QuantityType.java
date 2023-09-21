package com.cydeo.module;

public enum QuantityType {
    // 1 lb = 0.453592 kg
    // 1 ounce ≈ 28.35 g
    OUNCE("Ounce"), LB("Lb"), TBSP("Tbsp");
    private final String QuantityTypeValue;
     QuantityType(String value){
         this.QuantityTypeValue = value;
    }
    public String getQuantityTypeValue(){
         return QuantityTypeValue;
    }

}
