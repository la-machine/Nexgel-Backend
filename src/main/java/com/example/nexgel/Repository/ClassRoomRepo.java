package com.example.nexgel.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.nexgel.model.ClassRoom;

@Repository
public interface ClassRoomRepo {
    @Query("SELECT c FROM public.ClassRoom c WHERE c.name = ?1")
    ClassRoom findByClassRoom(String room);
}
