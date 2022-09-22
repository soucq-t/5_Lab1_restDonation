package com.example.donation.persistence;

import com.example.donation.domain.Donation;
import com.example.donation.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person,Integer> {
    @Query(
            """
            select person
            from  Person person
            where person.id= :id
            """
    )
    Optional<Person> findPersonById(long id );
//    Donation addDonation(long id);
}
