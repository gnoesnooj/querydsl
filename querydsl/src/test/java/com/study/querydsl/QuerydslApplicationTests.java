package com.study.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class QuerydslApplicationTests {

	@Autowired
	private EntityManager entityManager;

	@Test
	void contextLoads() {
		Hello hello = new Hello();

		entityManager.persist(hello);

		//jpaqueryfactory
		JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);

		// hello 조회
		QHello qHello = QHello.hello;

		Hello result = jpaQueryFactory.selectFrom(qHello).where(qHello.id.eq(1L)).fetchOne();

		Assertions.assertThat(result).isEqualTo(hello);
	}

}
