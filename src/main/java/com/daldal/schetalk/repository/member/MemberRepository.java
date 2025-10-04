package com.daldal.schetalk.repository.member;

import com.daldal.schetalk.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUsername(String username);

    @Query(value = "SELECT * \n" +
            "  FROM schetalk.Member\n" +
            " WHERE username =:username",nativeQuery = true)
    Optional<Member> duplicateNickName(@Param("username") String nickName);

}
