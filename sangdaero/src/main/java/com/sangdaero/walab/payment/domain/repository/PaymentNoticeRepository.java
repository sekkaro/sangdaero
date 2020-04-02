package com.sangdaero.walab.payment.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sangdaero.walab.payment.domain.entity.PaymentNoticeEntity;

public interface PaymentNoticeRepository extends JpaRepository<PaymentNoticeEntity, Long> {

}
