package org.document.common.utils;


public class ErrorCodes {

    // Category
    public static String CAT001 = "[CAT001] Category code must not null";
    public static String CAT002 = "[CAT002] Category description must not null";
    public static String CAT003 = "[CAT003] Category image must not null";
    public static String CAT004(Long categoryId) {
        return "[CAT004] Expected to find at least one category with category id "+ categoryId +" but found none.";
    }
    public static String CAT005(String categoryUuid) {
        return "[CAT005] Expected to find at least one category with category uuid "+ categoryUuid +" but found none.";
    }
    public static String CAT006 = "[CAT003] Category name must not null";

    // Product
    public static String PRD001 = "[PRD001] Product name must not null";
    public static String PRD002 = "[PRD002] Product category must not null";
    public static String PRD003 = "[PRD003] Product sub category must not null";
    public static String PRD004 = "[PRD004] Product unit must not null";
    public static String PRD005 = "[PRD005] Product stock must not null";
    public static String PRD006 = "[PRD006] Product minimum qty must not null";
    public static String PRD007 = "[PRD007] Product quantity must not null";
    public static String PRD008 = "[PRD008] Product description must not null";
    public static String PRD009 = "[PRD009] Product tax must not null";
    public static String PRD010 = "[PRD010] Product discount type must not null";
    public static String PRD011 = "[PRD011] Product price must not null";
    public static String PRD012 = "[PRD012] Product image must not null";

    public static String PRD013(String productUuid) {
        return "[PRD013] Expected to find at least one product with product uuid "+ productUuid +" but found none.";
    }

    // User
    public static String USR001 = "[USR001] First name must not null";
    public static String USR002 = "[USR002] Last name must not null";
    public static String USR003 = "[USR003] Age must not null";
    public static String USR004 = "[USR004] Date of birth must not null";
    public static String USR005 = "[USR005] Gender must not null";
    public static String USR006(String userUuid) {
        return "[USR006] Expected to find at least one category with user uuid "+ userUuid +" but found none.";
    }
    public static String USR007 = "[USR005] Username must not null";
    public static String USR008 = "[USR005] Email must not null";

    public static String USR009(String userName) {
        return "[USR009] No user found for this username: "+ userName;
    }

    public static String USR010(String userName) { return "[USR010] Username "+userName+" is already exist"; }
}
