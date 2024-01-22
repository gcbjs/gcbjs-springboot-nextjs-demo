import React from "react";
import Link from "next/link";
import {getAllNotes} from "../lib/server";
import SidebarNoteList from "../components/SidebarNoteList";

export default async function Sidebar() {

    const notes = await getAllNotes()
    return (
        <>
            <section className="col sidebar">
                <Link href={'/'} className="link--unstyled">
                    <section className="sidebar-header">
                        <img
                            className="logo"
                            src="/logo.svg"
                            width="22px"
                            height="20px"
                            alt=""
                            role="presentation"
                        />
                        <strong>React Notes</strong>
                    </section>
                </Link>
                <section className="sidebar-menu" role="menubar">
                    {/* SideSearchField */}
                </section>
                <nav>
                    {/* SidebarNoteList */}
                    <SidebarNoteList notes={notes}/>
                </nav>
            </section>
        </>
    )
}