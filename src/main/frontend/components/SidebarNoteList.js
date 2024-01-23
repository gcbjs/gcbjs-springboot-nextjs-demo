import SidebarNoteItem from "./SidebarNoteItem";
import {getAllNotes} from "@/lib/server";


export default async function NoteList() {

    // const sleep = ms => new Promise(r => setTimeout(r, ms));
    // await sleep(3000);
    const notes = await getAllNotes()

    if (notes.length === 0) {
        return <div className="notes-empty">
            {'No notes created yet!'}
        </div>
    }
    return (
        <ul className="notes-list">
            {notes.map((note) => {
                const { noteId } = note;
                return <li key={noteId}>
                    <SidebarNoteItem note={note}/>
                </li>
            })}
        </ul>
    )
}