import SidebarNoteItem from "./SidebarNoteItem";


export default async function NoteList({notes}) {

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