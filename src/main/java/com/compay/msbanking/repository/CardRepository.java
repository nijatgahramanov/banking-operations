package com.compay.msbanking.repository;

import com.compay.msbanking.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface CardRepository extends JpaRepository<Card, Long> {

    Optional<Card> findByIdAndActive(Long id, int i);;

    Optional<List<Card>> findByActive(int i);

    Optional<Card> findByNumber(String number);

    Optional<List<Card>> findByAccountId(Long id);
}
