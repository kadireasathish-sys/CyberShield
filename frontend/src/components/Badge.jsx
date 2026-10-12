export default function Badge({children,tone}){return <span className={`badge ${tone||String(children).toLowerCase().replaceAll(' ','-')}`}>{children}</span>}
