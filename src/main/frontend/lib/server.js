
export async function getAllNotes() {
   const data = await fetch('http://localhost:8080/note/getAllNotes')
   return data.json()
}

export async function addNote(note) {
   return null
}

export async function updateNote(note) {
   return null
}

export async function deleteNote(noteId) {
   return null
}