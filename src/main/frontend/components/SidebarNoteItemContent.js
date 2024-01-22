'use client';
import React from "react";
import {usePathname, useRouter} from "next/navigation";
import {useEffect, useRef, useTransition} from "react";

export default function SidebarNoteItemContent({
    id,
    title,
    children,
    expandedChildren,
                                               }) {

    const router = useRouter();
    const pathname = usePathname();
    const selected = pathname?.split('/') || null

    const [isPending] = useTransition()
    const [isExpanded, setIsExpanded] = React.useState(false);
    const isActive = id === selected;

    const itemRef = useRef(null);
    const prevTitleRef = useRef(title);

    useEffect(() => {
        if (title !== prevTitleRef.current) {
            prevTitleRef.current = title;
            itemRef.current.classList.add('flash');
        }
    }, [title]);

    return (
        <div
            ref={itemRef}
            onAnimationEnd={() => {
                itemRef.current.classList.remove('flash');
            }}
            className={[
                'sidebar-note-list-item',
                isExpanded ? 'note-expanded' : '',
            ].join(' ')}>
            {children}
            <button
                className="sidebar-note-open"
                style={{
                    backgroundColor: isPending
                        ? 'var(--gray-80)'
                        : isActive
                            ? 'var(--tertiary-blue)'
                            : '',
                    border: isActive
                        ? '1px solid var(--primary-border)'
                        : '1px solid transparent',
                }}
                onClick={() => {
                    const sidebarToggle = document.getElementById('sidebar-toggle')
                    if (sidebarToggle) {
                        sidebarToggle.checked = true
                    }
                    router.push(`/note/${id}`)
                }}>
                Open note for preview
            </button>
            <button
                className="sidebar-note-toggle-expand"
                onClick={(e) => {
                    e.stopPropagation();
                    setIsExpanded(!isExpanded);
                }}>
                {isExpanded ? (
                    <img
                        src="/chevron-down.svg"
                        width="10px"
                        height="10px"
                        alt="Collapse"
                    />
                ) : (
                    <img src="/chevron-up.svg" width="10px" height="10px" alt="Expand" />
                )}
            </button>
            {isExpanded && expandedChildren}
        </div>
    );
}