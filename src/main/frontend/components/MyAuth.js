import { signIn, signOut, auth } from "auth"
import React from "react";


function SignIn({
                    provider,
                    ...props
                }) {
    return (
        <form
            action={async () => {
                "use server"
                await signIn(provider)
            }}
        >
            <button {...props}>Sign In</button>
        </form>
    )
}

function SignOut(props) {
    return (
        <form
            action={async () => {
                "use server"
                await signOut()
            }}
        >
            <button {...props}>
                Sign Out
            </button>
        </form>
    )
}

export default async function MyAuth() {

    const session = await auth()

    return (
        <>
            {
                session?.user
                    ? <span style={{ display: "flex", "alignItems": "center" }}>{session?.user.name}<SignOut /></span>
                    : <SignIn />
            }
        </>
    )
}