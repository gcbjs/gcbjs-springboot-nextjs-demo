
async function fetchData(url, options) {
   try {
      const response = await fetch(url, options);

      if (response.ok) {
         const data = await response.json();
         return data.data;
      } else {
         console.error('接口调用失败');
         return null;
      }
   } catch (error) {
      console.error('发生错误:', error);
      return null;
   }
}

export async function getAllNotes() {
   const url = 'http://localhost:8080/note/getAllNotes';
   const options = {

   };

   return await fetchData(url, options);
}


export async function getNote(noteId) {
   const url = `http://localhost:8080/note/getNote?noteId=${noteId}`;
   const options = {
   };

   return await fetchData(url, options);
}

export async function addNote(note) {
   const url = 'http://localhost:8080/note/addNote';
   const options = {
      method: 'POST',
      headers: {
         'Content-Type': 'application/json',
      },
      body: JSON.stringify(note),
   };

   return await fetchData(url, options);
}


export async function updateNote(note) {
   const url = 'http://localhost:8080/note/updateNote';
   const options = {
      method: 'POST',
      headers: {
         'Content-Type': 'application/json',
      },
      body: JSON.stringify(note),
   };

   return await fetchData(url, options);
}


export async function deleteNote(noteId) {
   const url = `http://localhost:8080/note/deleteNote?noteId=${noteId}`;
   const options = {
      method: 'POST',
      headers: {
         'Content-Type': 'application/json',
      },
   };

   return await fetchData(url, options);
}
