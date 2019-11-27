package com.rubypaper.board.persistence;

import org.springframework.data.repository.CrudRepository;

import com.rubypaper.board.domain.Member;

public interface MemberRepository extends CrudRepository<Member, String> {

}
