
export async function getAllNotes() {
   const data = await fetch('http://localhost:8080/note/getAllNotes',{
      next: { revalidate: 10 }
   })
   return data.json()
}

export async function getNote(noteId) {
   const data = await fetch('http://localhost:8080/note/getNote?noteId='+noteId,{})
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