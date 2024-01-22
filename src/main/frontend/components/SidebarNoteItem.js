import SidebarNoteItemContent from "./SidebarNoteItemContent";
import dayjs from "dayjs";

export default function SidebarNoteItem({note}) {
    const {title,content='',updateTime,noteId} = note;
    return (
        <SidebarNoteItemContent
            id={noteId}
            title={note.title}
            expandedChildren={
                <p className="sidebar-note-excerpt">
                    {content.substring(0, 20) || <i>(No content)</i>}
                </p>
            }>
            <header className="sidebar-note-header">
                <strong>{title}</strong>
                <small>{dayjs(updateTime).format('YYYY-MM-DD hh:mm:ss')}</small>
            </header>
        </SidebarNoteItemContent>
    )
}