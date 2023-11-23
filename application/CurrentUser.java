/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package application;

import models.User;

public class CurrentUser {

    public static User ActiveUser;

    public static void SetActiveUser(User user) {
        ActiveUser = user;
    }
}
