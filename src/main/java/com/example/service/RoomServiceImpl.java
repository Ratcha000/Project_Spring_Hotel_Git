package com.example.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Savepoint;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.model.Room;
import com.example.repository.RoomRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements IRoomService {
	
	private final RoomRepository roomRepository;

	@Override
	public Room addNewRoom(MultipartFile photo, String roomType, BigDecimal roomPrice) throws IOException, SQLException, SerialException {
	        Room room = new Room();
	        room.setRoomType(roomType);
	        room.setRoomPrice(roomPrice);
		
	        if (!photo.isEmpty()) {
	            byte[] photoBytes = photo.getBytes();
	            Blob phoBlob = new SerialBlob(photoBytes);
	            room.setPhoto(phoBlob);
	        }
	        return roomRepository.save(room);
	    }
	
}
