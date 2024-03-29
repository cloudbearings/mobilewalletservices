/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mobilewallet.config;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author sasidhar
 */
@javax.ws.rs.ApplicationPath("MobileWallet")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<Class<?>>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.mobilewallet.services.AddReferralIncentiveService.class);
        resources.add(com.mobilewallet.services.BalanceService.class);
        resources.add(com.mobilewallet.services.DailyRewardsService.class);
        resources.add(com.mobilewallet.services.FeedbackService.class);
        resources.add(com.mobilewallet.services.ForgotPasswordService.class);
        resources.add(com.mobilewallet.services.GetNotificationsResource.class);
        resources.add(com.mobilewallet.services.LoginService.class);
        resources.add(com.mobilewallet.services.NotificationsService.class);
        resources.add(com.mobilewallet.services.NotificatonsService.class);
        resources.add(com.mobilewallet.services.RechargeHistoryService.class);
        resources.add(com.mobilewallet.services.RegisterService.class);
        resources.add(com.mobilewallet.services.SubmitQuestionService.class);
        resources.add(com.mobilewallet.services.UpdateGCMResource.class);
        resources.add(com.mobilewallet.services.UpdateUserProfileService.class);
        resources.add(com.mobilewallet.services.UserProfileService.class);
        resources.add(com.mobilewallet.services.UserQuestionsService.class);
        resources.add(com.mobilewallet.services.WalletHistoryService.class);
    } 
}
