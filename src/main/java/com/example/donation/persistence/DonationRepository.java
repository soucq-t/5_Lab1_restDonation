package com.example.donation.persistence;

import com.example.donation.domain.Donation;
import com.example.donation.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DonationRepository extends JpaRepository<Donation,Integer> {
@Query("""
    select donation.person
    from Donation donation
    group by donation.person
     having sum(donation.amount) > :min

""")
    List<Person> getMindDonated(long min);

    @Query("""
    select donation
    from Donation donation
    where donation.id= :id
""")
    Optional<Donation> getDonationById(long id);

    @Query("""
    select donation
    from Donation donation
    where donation.person.id= :id
""")
    Optional<List<Donation>> findAllDonationsOfAPerson(long id);

}
