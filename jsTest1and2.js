function test1(nums1, nums2, k) {
    const nums2Set = new Set(nums2);
    const result = [];
    
    for (const num of nums1) {
        const complement = k - num;
        if (nums2Set.has(complement)) {
            result.push([num, complement]);
        }
    }
    
    return result;
}


const nums1 = [4, 5, 6, 7, 0, 1];
const nums2 = [3, 9, 10, 11, 12, 19];
const k = 13;

console.log(test1(nums1, nums2, k)); 


function decipher(ciphertext, knownWord) {
    for (let shift = 0; shift < 26; shift++) {
        let decrypted = '';
        for (const char of ciphertext) {
            if (/[A-Za-z]/.test(char)) {
                const code = char.charCodeAt(0);
                const isUpperCase = char === char.toUpperCase();
                const base = isUpperCase ? 'A'.charCodeAt(0) : 'a'.charCodeAt(0);
                const decryptedCode = (code - base - shift + 26) % 26 + base;
                decrypted += String.fromCharCode(decryptedCode);
            } else {
                decrypted += char;
            }
        }
        if (decrypted.includes(knownWord)) {
            return decrypted;
        }
    }
    return null; 
}