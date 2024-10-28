package com.springboot.CinemaSystem.service.impl;


import com.springboot.CinemaSystem.entity.Notification;
import com.springboot.CinemaSystem.service.NotificationDao;

public class NotificationDaoImpl implements NotificationDao {

	@Override
	public boolean addNotification(Notification notification) {
		return false;
	}

	@Override
	public boolean sendNotification(int userID, int notificationID) {
		return false;
	}

	@Override
	public Notification getNotificationByID(int notificationID) {
		return null;
	}
}