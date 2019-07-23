package com.example.booksearchserver.service.base;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * 기본 서비스 (transactional 로 커밋 관리)
 */
@Transactional
public abstract class BaseService {
}
