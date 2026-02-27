package com.muf.modules.workflow.note;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @PostMapping("/create")
    public ResponseEntity<NoteResponse> createNote(@Valid @RequestBody CreateNoteRequest request) {
        NoteResponse response = noteService.createNote(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoteResponse> updateNote(@PathVariable Integer id, @Valid @RequestBody UpdateNoteRequest request) {
        NoteResponse response = noteService.updateNote(id, request);
        return ResponseEntity.ok(response);
    }
}
