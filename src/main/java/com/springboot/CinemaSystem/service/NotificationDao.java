package com.springboot.CinemaSystem.service;

import com.springboot.CinemaSystem.entity.Notification;

public interface NotificationDao {
	public boolean addNotification(Notification notification);
	public boolean sendNotification(int userID, int notificationID);
	public Notification getNotificationByID(int notificationID);


}