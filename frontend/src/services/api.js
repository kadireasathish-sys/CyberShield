const BASE=(import.meta.env.VITE_API_BASE||'/api').replace(/\/$/, '');
export async function api(path,options={}){const token=localStorage.getItem('cs_token');const headers={...(options.headers||{})};if(token)headers.Authorization=`Bearer ${token}`;if(!(options.body instanceof FormData))headers['Content-Type']='application/json';const r=await fetch(BASE+path,{...options,headers});if(!r.ok){let e={};try{e=await r.json()}catch{};throw new Error(e.message||`Request failed (${r.status})`)}return r.status===204?null:r.json()}
export {BASE};
