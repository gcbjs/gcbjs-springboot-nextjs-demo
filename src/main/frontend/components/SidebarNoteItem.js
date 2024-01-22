import SidebarNoteItemContent from "./SidebarNoteItemContent";
import dayjs from "dayjs";
import SidebarNoteItemHeader from "./SidebarNoteItemHeader";

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
            <SidebarNoteItemHeader title={title} updateTime={updateTime}/>
        </SidebarNoteItemContent>
    )
}